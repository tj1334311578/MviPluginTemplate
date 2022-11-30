package other.mvi.fragment.src.app_package

fun mviFragmentKt(
    applicationPackage: String?,
    fragmentClass: String,
    packageName: String,
    extraModel: Boolean,
    routePath:String?
) = """
package $packageName
    
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ${if (extraModel) "com.at.common.mvi.BaseMviFragment" else "com.at.common.binding.BaseBindingFragment"}
import ${applicationPackage}.databinding.Fragment${fragmentClass}Binding
import com.melancholy.router.annotation.annotation.Route
import dagger.hilt.android.AndroidEntryPoint

/**
 * @description:
 * @author:  stone
 * @email:
 * @date : 
 */
@AndroidEntryPoint
@Route(path = "/${routePath}/${fragmentClass}Fragment")
class ${fragmentClass}Fragment : ${if (extraModel) "BaseMviFragment" else "BaseBindingFragment"}<Fragment${fragmentClass}Binding${if (extraModel) ", ${fragmentClass}ViewModel" else ""}>() {
    override fun initialize(view: View, savedInstanceState: Bundle?) {

    }

    override fun loadData() {
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Fragment${fragmentClass}Binding {
        return Fragment${fragmentClass}Binding.inflate(inflater, container, false)
    }

    ${if (extraModel) "override fun getViewModelClass(): Class<${fragmentClass}ViewModel> {\n" +
        "        return ${fragmentClass}ViewModel::class.java\n" +
        "    }" else ""}
}
    
"""