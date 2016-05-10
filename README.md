# myView
# 自定义环形进度条
## 1.基本用法
<com.example.view_test.mView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:percent="80"
        app:textSize="50dp"/>
        
## 2.相关属性
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="mView">
        //中间进度条的数字显示的字体大小
        <attr name="textSize" format="dimension"/>
        //百分比
        <attr name="percent" format="float"/>
        //进度条底色
        <attr name="roundColor" format="color"/>
        //进度条颜色
        <attr name="proColor" format="color"/>
    </declare-styleable>
</resources>
