/**
 * �e�W���u��Workspace Cleanup Plugin��PreBuildCleanup�ݒ���ꗗ�\������X�N���v�g�B
 */

import hudson.plugins.ws_cleanup.*;
import org.jenkinsci.plugins.multiplescms.*;

/**
 * �W���u��Workspace Cleanup Plugin��PreBuildCleanup�ݒ���ꗗ�\������B
 *
 * @param job �W���u
 * @param target PreBuildCleanup
 */
def printPreBuildCleanupInfo(job, target) {
    if (target instanceof PreBuildCleanup) {
        print("[" + job.name + "]")
        print(" deleteDirs=" + target.deleteDirs)
        print(", cleanupParameter=" + target.cleanupParameter)
        print(", externalDelete=" + target.externalDelete)
        print(", patterns=[")
        if (target.patterns != null) {
            for (i = 0; i < target.patterns.size; i++) {
                if (i != 0) {
                    print(",");
                }
                print("[" + target.patterns[i] + "]");
            }
        }
        print("]")
        println();
    }
}

// Workspace Cleanup Plugin��PreBuildCleanup�ݒ���ꗗ�\������B
jenkins.model.Jenkins.instance.items.each { job ->
    job.buildWrappersList.each { wrapper ->
        printPreBuildCleanupInfo(job, wrapper);
    }
}
println();
