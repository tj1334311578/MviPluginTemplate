package other.mvi.activity.src.app_package

fun mviViewModel(
    packageName:String,
    activityClass: String
) ="""

package $packageName

import android.app.Application
import com.at.common.mvi.BaseAndroidViewModel
import com.at.common.mvi.IUiEffect
import com.at.common.mvi.IUiEvent
import com.at.common.mvi.IUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @description:
 * @author:  stone
 * @email:
 * @date : 
 */
 
/// 示例: 含有刷新状态
// data class PageUiState(
//     val pageStatus: PageStatus = PageStatus.Loading,
//     val refreshStatus: RefreshStatus = RefreshStatus.RefreshIdle,
//     val loadMoreStatus: LoadMoreStatus = LoadMoreStatus.LoadMoreIdle,
//     val data: MutableList<Any> = mutableListOf()
// ): IUiStat

// sealed class PageUiEvent: IUiEvent {
//     data class Type(val type: String?): PageUiEvent()
//     object InitLoadData: PageUiEvent()
//     object RefreshData: PageUiEvent()
//     object LoadDataMore: PageUiEvent()
// }
 
 data class ${activityClass}UiState(val msg:String?=null) :IUiState
 
 sealed class ${activityClass}UiEvent:IUiEvent
 
 sealed class ${activityClass}UiEffect:IUiEffect
   
@HiltViewModel
class ${activityClass}ViewModel @Inject constructor(application:Application):
    BaseAndroidViewModel<${activityClass}UiState,${activityClass}UiEvent,${activityClass}UiEffect>(application){
        override fun handleEvent(event:${activityClass}UiEvent){
        
        }
        
        override fun providerInitialState():${activityClass}UiState{
            return ${activityClass}UiState()
        }
    }
"""