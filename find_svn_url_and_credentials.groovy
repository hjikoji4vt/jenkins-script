/**
 * Subversion�̐ݒ肪����Ă���JOB����Repository URL��Credentials�̒l���擾����X�N���v�g�B
 */

import hudson.scm.*
import org.jenkinsci.plugins.multiplescms.*;

/**
 * Subversion��Repository URL��CredentialsId���o�͂���B<br>
 * �w�肵��SCM�̃C���X�^���X��Subversion�̂��̂ł͂Ȃ��ꍇ�͏o�͂��Ȃ��B
 *
 * @param job �W���u
 * @param scm SCM
 */
def printSvnLocationInfo(job, scm) {
    if (scm instanceof SubversionSCM) {
        for (location in scm.locations) {
            println("[" + job.name + "] URL=" + location.getURL() + ", CredId=" + location.credentialsId);
        }
    }
}

// Subversion��Repository URL��Credentials���ꊇ�擾����B
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
