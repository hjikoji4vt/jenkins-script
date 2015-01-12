/**
 * Subversionの設定がされているJOBを対象にCredentialsの値を一括更新するスクリプト。
 */

import hudson.scm.*
import org.jenkinsci.plugins.multiplescms.*;

/**
 * SubversionのCredentialsを更新する。<br>
 * 指定したSCMのインスタンスがSubversionでない場合は更新しない。
 * @param job ジョブ
 * @param scm SCM
 */
def updateCredentialsId(job, scm) {

    // 新CredentialsId
    def  newCredentialsId = "51634f66-4ce4-4e6f-9f54-3954cab1a565";

    if (scm instanceof SubversionSCM) {
        scm.locations.eachWithIndex() { location, index ->
            println("[" + job.name + "] before: URL=" + location.getURL() + ", CredId=" + location.credentialsId);
            newLocation = location.withCredentialsId(newCredentialsId);
            
            scm.locations[index] = newLocation;    // この段階で反映される。
            println("[" + job.name + "] after : URL=" + newLocation.getURL() + ", CredId=" + newLocation.credentialsId)
            job.save();
        }
    }
}

// SubversionのRepository URLとCredentialsを一括取得する。
jenkins.model.Jenkins.instance.items.each { job ->
    if (job.scm instanceof SubversionSCM) {
        // job.getScm()?.getType() =~ "Subversion"
        updateCredentialsId(job, job.scm);

    } else if (job.scm instanceof MultiSCM) {
        for (smc in job.scm.configuredSCMs) {
            updateCredentialsId(job, smc);
        }
    }
}
