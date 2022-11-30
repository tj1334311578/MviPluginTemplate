package other.mvi.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.util.*

val mviFragmentTemplate
    get() = template {
        name="mvi fragment"
        description="适用于 mviPlugin框架的fragment"
        minApi = MIN_API
        category = Category.Other
        formFactor= FormFactor.Mobile
        screens= listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val  fragmentClass = stringParameter {
            name="Fragment Name"
            default="Home"
            help="只输入名字，不要包含Fragment 注:使用大驼峰"
            constraints= listOf(Constraint.NONEMPTY)
        }

        val routePath= stringParameter {
            name="route path"
            default="route"
            help="请输入路由路径,如 home 生成:/home/fragment"
            constraints= listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        layoutName= stringParameter {
            name="Layout Name"
            default="fragment_home"
            help="请输入布局的名字"
            constraints= listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest={fragmentToLayout(fragmentClass.value.lowercase(Locale.getDefault())) }
        }
        val packageName= defaultPackageNameParameter

        val extraModel= booleanParameter {
            name="生成model"
            default=true
            help="默认勾选,如何存在,则生成相对应的model以及可生成repository,否则不生成"
        }

        // val apiClass:StringParameter = stringParameter {
        //     name="Api Name"
        //     default=""
        //     visible={extraModel.value}
        //     help="只输入相关model所需要的repository中的Api类名"
        //     constraints= listOf(Constraint.CLASS,Constraint.UNIQUE,Constraint.VALUES)
        // }

        widgets(
            TextFieldWidget(fragmentClass),
            TextFieldWidget(routePath),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(extraModel),
            // TextFieldWidget(apiClass)
        )
        recipe ={ data: TemplateData ->
            mviFragmentRecipe(
                data as ModuleTemplateData,
                fragmentClass.value,
                layoutName.value,
                packageName.value,
                extraModel.value,
                // apiClass.value,
                routePath.value
            )
        }

    }
val defaultPackageNameParameter
    get() = stringParameter {
        name="Package name"
        visible={!isNewModule}
        default="com.company.myapp"
        constraints= listOf(Constraint.PACKAGE)
        suggest={packageName}
    }