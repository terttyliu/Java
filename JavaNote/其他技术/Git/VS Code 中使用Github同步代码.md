# Git 基本使用

## Git 基本原理

一个本地仓库主要由git维护的三颗“树”组成。第一个是`工作目录` ，它持有实际文件；第二个是`暂存区（Index）` ，它用于临时保存改动；第三个是`HEAD` ，它指向最后一次提交的结果。

![img](https://www.runoob.com/manual/git-guide/img/trees.png)

### 分支

在你创建仓库的时候，*master* 是“默认的”分支。在其他分支上进行开发，完成后再将它们合并到主分支上。

![img](https://www.runoob.com/manual/git-guide/img/branches.png)

## Git 基本操作

1. 创建**repository**

   1. 本地初始化

      `git init`

   1. 检出仓库

      `git clone /path/to/repository` 本地检出

      `git clone username@host:/path/to/repository` 远程检出

2. **添加**和**提交**

   1. 将文件添加到暂存区

      `git add <filename>` 或`git add *`

   1. 实际提交改动

      `git commit -m "代码提交信息"`

      此时，你的改动已经提交到了HEAD，但还没有同步到远程仓库。

3. 推送改动

   `git push origin master` 其中master可以换成你想推送的任何分支。

4. 分支操作

   1. 创建分支

      `git checkout -b feature_x`

   2. 切换回主分支

      `git checkout master`

   3. 把新建的分支删掉

      `git branch -d feature_x`

   4. 推送分支到远程仓库

      `git push origin <branch>`

5. 更新与合并

    1. 更新你的本地仓库至最新改动

       `git pull`

    2. 合并其他分支到当前分支

       `git merge <branch>`

       有时合并会出现**冲突**，需要手动修改这些文件来合并**冲突**，改完后执行`git add <filename>` 将它们标记为合并成功。

6. 其他操作

   丢弃你在本地的所有改动与提交，可以到服务器上获取最新的版本历史，并将你本地主分支指向它

   `git fetch origin`

   `git reset --hard origin/master`
