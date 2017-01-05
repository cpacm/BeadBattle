## Bead Battle 

`BeadBattle` 是很早以前（2年前）写的一个小游戏项目，当时是使用 `SurfaceView` 加上线程处理来进行实现的，代码写的有点糟糕而且游戏优化也不行，从而导致运行时帧数不足。

现在重写了整个项目，全部使用 `ValueAnimator` 来进行动画，代码放在 `BeadBattle` 模块中。之前的代码也删掉了，想要吐槽我以前的糟糕代码的话可以找找提交记录去。

![BeadBattle](http://7xi4up.com1.z0.glb.clouddn.com/beadbattle.gif)  

## 游戏玩法
1. 连接然后消除
2. 只能选择自己相邻的格子（允许对角），且要求是同一种颜色
3. 消除后返回相应的分数

至于时间限制啊，生命值什么的就懒得弄了，玩家无限的玩下去好了 _(:з」∠)_

## License
Copyright 2017 cpacm

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.



