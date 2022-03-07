# Git
## 1. branch
```shell
    # branch 조회
    git branch
    # branch 생성
    git branch {branch_name}
    # branch 이름 변경
    git branch -m {pre_branch_name} {post_branch_name}
    # branch 전환
    git checkout {branch_name}
    # branch 생성과 전환
    git checkout -b {branch_name}
```
### 전략
* git-flow 전략은 branch를 master , develop , feature , release , hotfix로 
나눠서 개발한다. master , develop가 main 역할을 하고 master는 배포가능한 상태인 
branch이고 develop은 개발중이고 다음에 배포할 branch로 통합 branch 역할을 한다.
feature , release , hotfix가 supporting 역할을 하고 feature는 develop으로부터
분기되어서 기능을 개발하는 branch이고 release는 develop에서 배포를 위한
버그 수정용으로 사용하는 branch이고 hotfix는 master에서 배포를 위한 버그 수정용으로
사용되는 branch이다.
* github-flow 전략은 branch를 master와 master에서 분기된 branch로 나눠서 개발한다.
master는 배포가능한 상태인 branch이다.
