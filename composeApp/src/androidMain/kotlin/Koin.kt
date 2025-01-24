import com.plcoding.room_cmp.database.getUserDataBase
import database.user.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val appModuleAn: Module = module {
    single { getUserDataBase(androidContext()) }
    single { get<UserDatabase>().userDao() }
}