### bigDuang
A course project for sysu_software system analysis and design. 


####整体指引
1. 项目所有介绍、相关说明、除了源码之外的制品请移步:[Wiki](https://github.com/1900zyh/bigDuang/wiki/%E4%B8%BB%E9%A1%B5)
2. 开发过程中的todolist和问题请移步:[Issues](https://github.com/1900zyh/bigDuang/issues)


####文件结构
这个我瞎写的不用太认真看
bigDuang/                      
├── data/     存放数据               
│     ├── train.csv        训练集数据          
│     ├── test.csv         测试集数据           
│     └── result.csv       预测结果                  
|              
├── io.h                   读写文件、数据转化，可以在这里设置文件处理的格式        
├── io.cpp            
└── lrgd.h                 线性回归+梯度下降模型 类          
└── lrgd.cpp                                      
└── main.cpp               程序入口，可以设置学习速率、迭代次数等       
└── Makefile               生成可执行文件  main           
└── readme.txt             你正在看的文件         
└── debug.txt              执行时，你可以通过重定向将一些debug信息写在这里   
