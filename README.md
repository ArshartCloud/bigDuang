### bigDuang
A course project for sysu_software system analysis and design. 


####整体指引
1. 项目所有介绍、相关说明、除了源码之外的制品请移步:[Wiki](https://github.com/1900zyh/bigDuang/wiki/%E4%B8%BB%E9%A1%B5)
2. 开发过程中的todolist和问题请移步:[Issues](https://github.com/1900zyh/bigDuang/issues)


####文件结构
这个我瞎写的不用太认真看
bigDuang/
├── bin/                         可执行二进制文件目录，shell脚本在编译前删除此目录并重新创建此目录，故没有此目录不会影响脚本运行
├── build/                       构建目录，shell脚本在编译前删除此目录并重新创建此目录，故没有此目录不会影响脚本运行
├── future_net/                  代码目录
│     ├── lib/
│     │     ├── lib_io.h         读写文件的头文件
│     │     ├── lib_record.h     将输出结果记录到缓冲区的头文件
│     │     └── lib_time.h       打印时间的头文件
│     ├── CMakeLists.txt         cmake
│     ├── future_net.cpp         main函数源文件
│     ├── route.cpp              你要写代码的源文件
│     └── route.h                你要写代码的头文件
├── batch.sh                     编译、链接、打包批处理脚本
└── README.md                   你正在看的文件 -_-" 这不用介绍了吧
