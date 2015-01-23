/**
 * �e�W���u��Workspace Cleanup Plugin��PreBuildCleanup�ݒ���ꊇ�ǉ�����X�N���v�g�B
 */

import hudson.plugins.ws_cleanup.*;
import org.jenkinsci.plugins.multiplescms.*;

/**
 * �w�肵��BuildWrappersList��PreBuildCleanup�̃C���X�^���X�����݂��邩�𔻒肷��B
 * 
 * @param buildWrappersList BuildWrappersList
 * @return �C���X�^���X�����݂���ꍇ��true���擾����B
 */
def existsPreBuildCleanup(buildWrappersList) {

    buildWrappersList.each { wrapper ->
        if (target instanceof PreBuildCleanup) {
            return true;
        }
    }
    return false;
}

// Workspace Cleanup Plugin��PreBuildCleanup�ݒ��ǉ�����B
jenkins.model.Jenkins.instance.items.each { job ->

    if (!existsPreBuildCleanup(job.buildWrappersList)) {
        // �ݒ肪���݂��Ȃ��ꍇ�͒ǉ�����B
        target = new PreBuildCleanup(null, false, "", "");
        job.buildWrappersList.add(target);
        job.save();
    }
}
println();
