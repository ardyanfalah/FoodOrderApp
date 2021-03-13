package e.ardya.foodorderapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.adapter.RecyclerAdapter

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        try
//        {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.hide()
//        }catch (e: Exception){
//            Log.d("Error ",e.toString())
//        }

        window.statusBarColor = Color.parseColor("#262626")


        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.second_fragment, R.id.third_fragment)) //  IDs of fragments you want without the ActionBar home/up button

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
         appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.navigation_home || destination.id == R.id.navigation_dashboard || destination.id == R.id.navigation_notifications){
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
