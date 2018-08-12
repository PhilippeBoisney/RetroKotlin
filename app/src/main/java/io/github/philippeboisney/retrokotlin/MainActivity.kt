package io.github.philippeboisney.retrokotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import io.github.philippeboisney.retrokotlin.base.BaseActivity
import io.github.philippeboisney.retrokotlin.model.User
import io.github.philippeboisney.retrokotlin.viewmodel.UserViewModel

class MainActivity : BaseActivity() {

    // FOR DESIGN
    @BindView(R.id.activity_main_avatar) lateinit var imageViewAvatar: ImageView
    @BindView(R.id.activity_root_view) lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.activity_main_name) lateinit var textViewName: TextView
    @BindView(R.id.activity_main_company) lateinit var textViewCompany: TextView
    @BindView(R.id.activity_main_blog) lateinit var textViewBlog: TextView

    // FOR DATA
    private lateinit var viewModel: UserViewModel

    // OVERRIDING
    override fun getLayoutById(): Int = R.layout.activity_main

    override fun configureDesign() {
        this.configureRefreshLayout()
        this.configureViewModel()
        this.observeData()
        this.fetchUserOnGithubApi()
    }

    // CONFIGURATION
    private fun configureViewModel(){
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)[UserViewModel::class.java];
    }

    private fun observeData(){
        this.viewModel.user
                .observe(this, Observer { it?.let { this.updateUI(it) } })
        this.viewModel.isLoading
                .observe(this, Observer { it?.let { this.updateRefreshLayout(it) } })
        this.viewModel.errorMessage
                .observe(this, Observer { it?.let { Log.e("TAG", "Throw an error : $it") } })
    }

    private fun configureRefreshLayout(){
        this.swipeRefreshLayout.setOnRefreshListener { this.fetchUserOnGithubApi() }
    }

    // PROCESSING
    private fun fetchUserOnGithubApi(){
        this.viewModel.getUser()
    }

    // UPDATE UI
    private fun updateUI(user: User){
        this.textViewName.text = user.name
        this.textViewCompany.text = user.company
        this.textViewBlog.text = user.blog
        Glide.with(this).load(user.avatarUrl).apply(RequestOptions.circleCropTransform()).transition(DrawableTransitionOptions.withCrossFade()).into(this.imageViewAvatar)
    }

    private fun updateRefreshLayout(isRefreshing: Boolean){
        this.swipeRefreshLayout.isRefreshing = isRefreshing
    }
}
