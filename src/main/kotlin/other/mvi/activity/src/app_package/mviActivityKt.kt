package other.mvi.activity.src.app_package

fun mviActivityKt(
    applicationPackage: String?,
    activityClass: String,
    packageName: String,
    extraModel: Boolean,
    routePath: String?
) = """
package $packageName

import android.os.Bundle
${
    if (extraModel) {
        "import com.at.common.mvi.BaseMviActivity"
    } else {
        "import com.at.common.binding.BaseBindingActivity"
    }
}
import ${applicationPackage}.databinding.Activity${activityClass}Binding
import com.melancholy.router.annotation.annotation.Route
import dagger.hilt.android.AndroidEntryPoint

/**
 * @description:
 * @author:  stone
 * @email:
 * @date :  
 */
@AndroidEntryPoint
@Route(path = "/${routePath}/${activityClass}Activity")
class ${activityClass}Activity : ${if (extraModel) "BaseMviActivity" else "BaseBindingActivity"}<Activity${activityClass}Binding${if (extraModel) ", ${activityClass}ViewModel" else ""}>() {
    override fun initialize(savedInstanceState: Bundle?) {

    }

    override fun loadData() {
    
    }
    
    override fun createViewBinding(): Activity${activityClass}Binding {
        return Activity${activityClass}Binding.inflate(layoutInflater)
    }

   ${
    if (extraModel)
        "override fun getViewModelClass(): Class<${activityClass}ViewModel> {\n" +
                "        return ${activityClass}ViewModel::class.java\n" +
                "    }" else ""
} 
}
"""