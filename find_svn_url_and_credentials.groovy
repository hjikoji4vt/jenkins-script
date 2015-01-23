/**
 * Subversionの設定がされているJOBからRepository URLとCredentialsの値を取得するスクリプト。
 */

import hudson.scm.*
import org.jenkinsci.plugins.multiplescms.*;

/**
 * SubversionのRepository URLとCredentialsIdを出力する。<br>
 * 指定したSCMのインスタンスがSubversionのものではない場合は出力しない。
 *
 * @param job ジョブ
 * @param scm SCM
 */
def printSvnLocationInfo(job, scm) {
    if (scm instanceof SubversionSCM) {
        for (location in scm.locations) {
            println("[" + job.name + "] URL=" + location.getURL() + ", CredId=" + location.credentialsId);
        }
    }
}

// SubversionのRepository URLとCredentialsを一括取得する。
jenkins.model.Jenkins.instance.items.each { job ->
    if (job.scm instanceof SubversionSCM) {
        // job.getScm()?.getType() =~ "Subversion"
        printSvnLocationInfo(job, job.scm);

    } else if (job.scm instanceof MultiSCM) {
        for (smc in job.scm.configuredSCMs) {
            printSvnLocationInfo(job, smc);
        }
    }
}
println();
