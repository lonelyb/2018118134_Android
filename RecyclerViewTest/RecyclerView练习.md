#实验目的
掌握RecyclerView的基本用法和RecyclerView的点击事件
#实验内容
在friut_item.xml文件中ImageView用于显示水果的图片，TextView用于显示水果的名称  
 
    <ImageView
        android:id="@+id/fruit_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"/>

    <TextView
        android:id="@+id/fruit_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left"
        android:layout_marginTop="10dp"/>  

瀑布流布局，将布局的列数设置成3，把布局设置成纵向排列，并构造getRandomLenghName（）方法，使得各个子项能看出明显的效果
   
     StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
     private String getRandomLenghName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder= new StringBuilder();
        for (int i=0; i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }  
运行程序，实验结果如下  
![启动程序](/RecyclerViewTest/img/捕获.PNG)  
![启动程序](/RecyclerViewTest/img/捕获1.PNG)  
在适配器中注册点击事件

        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"你点击了" + fruit.getName() + "的文字",
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"你点击了" + fruit.getName() + "的图片",
                        Toast.LENGTH_SHORT).show();
            }
        });


实验结果如下  
![启动程序](/RecyclerViewTest/img/捕获3.PNG)  
![启动程序](/RecyclerViewTest/img/捕获4.PNG)