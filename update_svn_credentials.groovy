/**
 * Subversion�̐ݒ肪����Ă���JOB��Ώۂ�Credentials�̒l���ꊇ�X�V����X�N���v�g�B
 */

import hudson.scm.*
import org.jenkinsci.plugins.multiplescms.*;

/**
 * Subversion��Credentials���X�V����B<br>
 * �w�肵��SCM�̃C���X�^���X��Subversion�łȂ��ꍇ�͍X�V���Ȃ��B
 * @param job �W���u
 * @param scm SCM
 */
def updateCredentialsId(job, scm) {

    // �VCredentialsId
    def  newCredentialsId = "51634f66-4ce4-4e6f-9f54-3954cab1a565";

    if (scm instanceof SubversionSCM) {
        scm.locations.eachWithIndex() { location, index ->
            println("[" + job.name + "] before: URL=" + location.getURL() + ", CredId=" + location.credentialsId);
            newLocation = location.withCredentialsId(newCredentialsId);
            
            scm.locations[index] = newLocation;    // ���̒i�K�Ŕ��f�����B
            println("[" + job.name + "] after : URL=" + newLocation.getURL() + ", CredId=" + newLocation.credentialsId)
            job.save();
        }
    }
}

// Subversion��Repository URL��Credentials���ꊇ�擾����B
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
