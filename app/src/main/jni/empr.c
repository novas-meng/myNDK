#include <jni.h>
#include <stdio.h>
#include <math.h>
#include <android/log.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <sys/epoll.h>
jstring Java_com_example_novas_myndk_NDK_getMessage(JNIEnv *env,jobject jobject1)
{
    jint var1=9;
    jint var2=10;
    return (*env)->NewStringUTF(env,"mvdsdasdsa name is 123456");
}
jint Java_com_example_novas_myndk_NDK_getSum(JNIEnv *env,jobject jobject1,jintArray array)
{
   // jint *start=(*env)->GetIntArrayElements(env,array,0 );
  //  jint size=(*env)->GetArrayLength(env,array);
  //  jint sum=0;
    jint i=0;
    jint width=0;
    jint height=0;
    for(i=0;i<100000;i++)
    {
        width=width+1;
    }
    for( i=0;i<1000000;i++)
    {
        height=height+1;
    }
    jint a=-200;
    jint b=fabs(a);
    jint c=900;
    return b+c;
}
int Java_com_example_novas_myndk_NDK_printObject(JNIEnv *env,jobject object,jobject jobject1)
{
   // jclass jclass1=(*env)->FindClass(env,"com/example/novas/myndk/Shape");
    jclass jclass1=(*env)->GetObjectClass(env,jobject1);
    if(jclass1==NULL)
    {
        return -1;
    }
    jfieldID jfieldID1=(*env)->GetFieldID(env,jclass1,"width","I");
    jfieldID jfieldID2=(*env)->GetFieldID(env,jclass1,"cir","I");
    jfieldID jfieldID3=(*env)->GetFieldID(env,jclass1,"demo","I");
    if(jfieldID1==NULL||jfieldID2==NULL||jfieldID3==NULL)
    {
        return -1;
    }
    jint width=(*env)->GetIntField(env,jobject1,jfieldID1);
    jmethodID jmethodID1=(*env)->GetMethodID(env,jclass1,"getCircular","()I");
    jint res=(*env)->CallIntMethod(env,jobject1,jmethodID1);
    res=(*env)->GetIntField(env,jobject1,jfieldID2)+(*env)->GetIntField(env,jobject1,jfieldID3);
    (*env)->SetIntField(env,jobject1,jfieldID1,3000);
    char ch[50];
    FILE *pFile = fopen("sdcard/3.txt", //打开文件的名称
                       "w"); // 文件打开方式 如果原来有内容也会销毁
//向文件写数据
   fwrite ("hello", //要输入的文字
           1,//文字每一项的大小 以为这里是字符型的 就设置为1 如果是汉字就设置为45, //单元个数 我们也可以直接写5
           5,
           pFile //我们刚刚获得到的地址
    );
    fclose(pFile);

    FILE *f1=fopen("sdcard/1.txt","r");
    FILE *f=fopen("init.rc","r");
  //  mkfifo("demo", S_IFIFO | 0666);
   // int wfd = open("demo", O_WRONLY);
  //  char buf[10]="mengfan";
  //  write(wfd,buf,strlen(buf));
  //  close(wfd);
    char buf[1024] = { '\0' };
    int i;
  int rfd = open("demo", O_RDONLY);
    __android_log_print(ANDROID_LOG_INFO, "native-activity","pid=%d\n",rfd);

    if (chmod("init.rc", S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH) < 0)
    {
        __android_log_print(ANDROID_LOG_INFO, "native-activity","file error");

    }
    char c[50];
    fread(c,1,40,f1);
    //printf("src=%d\n",c);
    __android_log_print(ANDROID_LOG_INFO, "native-activity","%s",c);

    return res;
}
void Java_com_example_novas_myndk_NDK_changeM(JNIEnv *env,jobject jobject1,jobject currentobject)
{
    jclass jclass1=(*env)->FindClass(env,"com/example/novas/myndk/Shape");
    jfieldID jfieldID1=(*env)->GetFieldID(env,jclass1,"mn","Lcom/example/novas/myndk/M;");
    if(jfieldID1==NULL)
    {
        printf("cant find M");
    }
    jclass jclass2=(*env)->FindClass(env,"com/example/novas/myndk/M");
    if(jclass2==NULL)
    {
       printf("cant find class M");
    }
    jfieldID jfieldID2=(*env)->GetStaticFieldID(env,jclass2,"SMALL","Lcom/example/novas/myndk/M;");
    jobject jobject2=(*env)->GetStaticObjectField(env,jclass2,jfieldID2);
    (*env)->SetObjectField(env,currentobject,jfieldID1,jobject2);
}