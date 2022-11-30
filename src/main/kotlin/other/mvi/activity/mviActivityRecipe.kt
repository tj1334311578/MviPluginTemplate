package other.mvi.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvi.activity.src.app_package.mviActivityKt
import other.mvi.activity.src.app_package.mviViewModel
import other.mvi.activity.res.layout.mviActivityXml

fun RecipeExecutor.mviActivityRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    layoutName: String,
    packageName: String,
    extraModel:Boolean,
    // apiClass: String?,
    routePath:String?
    ){
    val (projectData,srcOut,resOut) =moduleData
    val ktOrJavaExt=projectData.language.extension
    val mviActivity= mviActivityKt(projectData.applicationPackage,activityClass,packageName,extraModel,routePath)

    // 保存activity
    save(mviActivity,srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))
    // 保存xml
    save(mviActivityXml(packageName,activityClass),resOut.resolve("layout/${layoutName}.xml"))

    if(extraModel){
        // 保存viewModel
        save(mviViewModel(packageName,activityClass),srcOut.resolve("${activityClass}ViewModel.${ktOrJavaExt}"))
    }
    // takeIf { apiClass?.isNotEmpty()==true }?.apply {
    //     save(mviRepository(packageName,activityClass,apiClass),srcOut.resolve("${activityClass}Repository.${ktOrJavaExt}"))
    // }
}