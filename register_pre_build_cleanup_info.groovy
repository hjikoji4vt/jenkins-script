/**
 * 各ジョブのWorkspace Cleanup PluginのPreBuildCleanup設定を一括追加するスクリプト。
 */

import hudson.plugins.ws_cleanup.*;
import org.jenkinsci.plugins.multiplescms.*;

/**
 * 指定したBuildWrappersListにPreBuildCleanupのインスタンスが存在するかを判定する。
 * 
 * @param buildWrappersList BuildWrappersList
 * @return インスタンスが存在する場合はtrueを取得する。
 */
def existsPreBuildCleanup(buildWrappersList) {

    buildWrappersList.each { wrapper ->
        if (target instanceof PreBuildCleanup) {
            return true;
        }
    }
    return false;
}

// Workspace Cleanup PluginのPreBuildCleanup設定を追加する。
jenkins.model.Jenkins.instance.items.each { job ->

    if (!existsPreBuildCleanup(job.buildWrappersList)) {
        // 設定が存在しない場合は追加する。
        target = new PreBuildCleanup(null, false, "", "");
        job.buildWrappersList.add(target);
        job.save();
    }
}
println();
