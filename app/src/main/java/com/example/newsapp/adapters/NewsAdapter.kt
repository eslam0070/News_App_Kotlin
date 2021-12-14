package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    private var onItemClickListener : ((Article)->Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }
    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(p0: Article, p1: Article): Boolean {
            return p0.url == p1.url
        }

        override fun areContentsTheSame(p0: Article, p1: Article): Boolean {
            return p0 == p1
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_article_preview,p0,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }





    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}