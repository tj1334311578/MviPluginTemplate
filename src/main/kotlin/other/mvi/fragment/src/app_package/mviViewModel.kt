package other.mvi.fragment.src.app_package

fun  mviViewModel(
    packageName:String,
    fragmentClass:String
)="""
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

data class ${fragmentClass}UiState(val msg:String?=null):IUiState
sealed class ${fragmentClass}UiEvent:IUiEvent
sealed class ${fragmentClass}UiEffect:IUiEffect
@HiltViewModel
class ${fragmentClass}ViewModel @Inject constructor(application: Application):
    BaseAndroidViewModel<${fragmentClass}UiState, ${fragmentClass}UiEvent, ${fragmentClass}UiEffect>(application) {
    override fun handleEvent(event: ${fragmentClass}UiEvent) {
    }

    override fun providerInitialState(): ${fragmentClass}UiState {
        return ${fragmentClass}UiState()
    }
}    
"""