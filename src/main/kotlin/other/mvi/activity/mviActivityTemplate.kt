package other.mvi.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.util.*

val mviActivityTemplate
    get() = template {
        name="mvi activity"
        description="适用于 mviPlugin框架的activity"
        minApi = MIN_API
        category = Category.Other
        formFactor=FormFactor.Mobile
        screens= listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName:StringParameter

        val  activityClass = stringParameter {
            name="Activity Name"
            default="Main"
            help="只输入名字，不要包含Activity 注:使用大驼峰"
            constraints= listOf(Constraint.NONEMPTY)
        }

        val routePath= stringParameter {
           name="route path"
           default="route"
           help="请输入路由路径,如 home 生成:/home/activity"
           constraints= listOf(Constraint.UNIQUE,Constraint.NONEMPTY)
        }

        layoutName= stringParameter {
            name="Layout Name"
            default="activity_main"
            help="请输入布局的名字"
            constraints= listOf(Constraint.LAYOUT,Constraint.UNIQUE,Constraint.NONEMPTY)
            suggest={ activityToLayout(activityClass.value.lowercase(Locale.getDefault())) }
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
            TextFieldWidget(activityClass),
            TextFieldWidget(routePath),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(extraModel),
            // TextFieldWidget(apiClass)
        )
        recipe ={ data:TemplateData->
            mviActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value,
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

