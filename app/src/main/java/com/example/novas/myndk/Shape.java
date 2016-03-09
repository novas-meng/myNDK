package com.example.novas.myndk;

/**
 * Created by novas on 2016/1/30.
 */
enum M
{
    BIG,SMALL;
}
public class Shape
{
    int width=0 ;
    int height=0;
    private int cir=20;
    final int demo=4;
     M mn=M.BIG;
    String text="fasdf";
    public Shape()
    {

    }
    public Shape(int width,int height)
    {
        this.width=width;
        this.height=height;
    }
    public int getCircular()
    {
        for(int i=0;i<100000;i++)
        {
            width=width+1;
        }
        for(int i=0;i<1000000;i++)
        {
            height=height+1;
        }
        return this.width*2+this.height*2;
    }
}
