package other.mvi.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvi.fragment.src.app_package.mviFragmentKt
import other.mvi.fragment.src.app_package.mviViewModel
import other.mvi.fragment.res.layout.mviFragmentXml

fun RecipeExecutor.mviFragmentRecipe(
    moduleData: ModuleTemplateData,
    fragmentClass: String,
    layoutName: String,
    packageName: String,
    extraModel:Boolean,
    // apiClass: String?,
    routePath:String?
){
    val (projectData,srcOut,resOut) =moduleData
    val ktOrJavaExt=projectData.language.extension
    val mviFragment= mviFragmentKt(projectData.applicationPackage,fragmentClass,packageName,extraModel,routePath)

    // 保存Fragment
    save(mviFragment,srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mviFragmentXml(packageName,fragmentClass),resOut.resolve("layout/${layoutName}.xml"))

    if(extraModel){
        // 保存viewModel
        save(mviViewModel(packageName,fragmentClass),srcOut.resolve("${fragmentClass}ViewModel.${ktOrJavaExt}"))
    }
    // takeIf { apiClass?.isNotEmpty()==true }?.apply {
    //     save(mviRepository(packageName,activityClass,apiClass),srcOut.resolve("${activityClass}Repository.${ktOrJavaExt}"))
    // }
}
