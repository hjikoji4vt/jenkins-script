/**
 * 各ジョブのWorkspace Cleanup PluginのPreBuildCleanup設定を一覧表示するスクリプト。
 */

import hudson.plugins.ws_cleanup.*;
import org.jenkinsci.plugins.multiplescms.*;

/**
 * ジョブのWorkspace Cleanup PluginのPreBuildCleanup設定を一覧表示する。
 *
 * @param job ジョブ
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

// Workspace Cleanup PluginのPreBuildCleanup設定を一覧表示する。
jenkins.model.Jenkins.instance.items.each { job ->
    job.buildWrappersList.each { wrapper ->
        printPreBuildCleanupInfo(job, wrapper);
    }
}
println();
