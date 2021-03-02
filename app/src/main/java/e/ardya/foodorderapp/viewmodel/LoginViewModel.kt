package e.ardya.foodorderapp.viewmodel

import androidx.lifecycle.MutableLiveData
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.net.service.UserService
import e.ardya.foodorderapp.utils.helper.SessionHelper

class LoginViewModel: BaseVM() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

//    fun clickLogin() {
//        if (email.value != null && password.value != null) {
//            dataLoading.postValue(true)
//            UserService.login(email.value!!, password.value!!, {
//                if (it?.user?.roleDataId == StaticValue.ROLE_FASKES || it?.user?.roleDataId == StaticValue.ROLE_DONATUR) {
//                    SessionHelper.save("name", it.user?.name ?: "-")
//                    SessionHelper.save("profilePicture", it.user?.profilePicture ?: "")
//                    SessionHelper.save("username", username.value)
//                    SessionHelper.save("password", password.value)
//                    SessionHelper.save("token", "${it.tokenType} ${it.accessToken}")
//                    SessionHelper.save("id", it.user?.id)
//                    SessionHelper.save("email", it.user?.email)
//                    SessionHelper.save("phone1", it.user?.mobilePhone)
//                    SessionHelper.save("roleId", it.user?.roleDataId)
//                    SessionHelper.save("roleName", it.user?.roleDataName)
//                    getMe()
//                    dataLoading.postValue(false)
//                } else {
//                    dataLoading.postValue(false)
//                }
//
//            }, {
//                dataLoading.postValue(false)
//                toastMessage.postValue(it ?: "Silahkan coba lagi")
//            })
//        } else {
//            toastMessage.postValue("Username & Password tidak boleh kosong.")
//        }
//    }

}