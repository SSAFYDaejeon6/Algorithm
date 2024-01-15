
# SSAFY 11기 대전 6반 알고리즘 스터디

## 스터디 멤버

|깃허브|[korno1](https://github.com/korno1)|[XXX](https://github.com/XXX)|[XXX](https://github.com/XXX)|[XXX](https://github.com/XXX)| [XXX](https://github.com/XXX)|
|:---:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|
|백준|[korno1](https://solved.ac/profile/korno1)|[XXX](https://solved.ac/profile/XXX)|[XXX](https://solved.ac/profile/XXX)|[XXX](https://solved.ac/profile/XXX)|[XXX](https://solved.ac/profile/XXX)
|언어|<img src = "https://img.shields.io/badge/C++-00599C?style=flat-square&logo=cplusplus&logoColor=white"/> <img src = "https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/>|<img src = "https://img.shields.io/badge/C++-00599C?style=flat-square&logo=cplusplus&logoColor=white"/>|<img src = "https://img.shields.io/badge/C++-00599C?style=flat-square&logo=cplusplus&logoColor=white"/>| <img src = "https://img.shields.io/badge/C++-00599C?style=flat-square&logo=cplusplus&logoColor=white"/>| <img src = "https://img.shields.io/badge/C++-00599C?style=flat-square&logo=cplusplus&logoColor=white"/>|

## 규칙
```
1. 일주일에 3문제를 정하고 월, 수, 금 기준으로 스터디를 진행
2. 난이도는 백준 실버 2 ~ 3 수준의 문제
3. 플랫폼은 백준, SWEA, 프로그래머스
4. 문제 선택은 번갈아가면서
5. 비대면을 기본으로 하고 대면은 필요 시 아침 및 점심시간 활용
6. 문제를 안풀경우 벌금 1000원, 못풀경우는 구글링 해서 이해하고 오기
	- 벌금의 사용처는 추후 회의를 통해 정함
7. 언어는 어떤걸 사용해도 무관하지만 자바 권장
	- 자바 이외에 언어는 다른 사람이 알아 들을 수 있게 설명
```

## 저장소 연결
```
1. 현재 레포지토리(중앙저장소)를 개인 저장소로 fork (원격 저장소)
2. fork한 저장소를 clone
	- ex) git clone https://github.com/korno1/Algorithm_ssafy.git
3. git remote -v 로 origin에 fork한 저장소 확인
4. 중앙 저장소 추가 (upstream)
	- git remote add upstream https://github.com/SSAFYDaejeon6/Algorithm.git
```

## 커밋 방법
```
1. 중앙 저장소의 자료를 pull (반드시 push전에 pull 먼저 해야함)
	- git pull upstream main
2. 로컬 저장소에서 staging 영역에 commit할 파일 올리기
	- git add . (작업한 파일 전체 올라감)
3. commit
	- git commit -m "쓸말"
4. 원격 저장소에 push
	- git push origin main
```

## Merge 방법
```
1. fork한 원격 저장소에 들어가서 Pull request 클릭
2. new pull request 클릭
3. 원격 저장소에서 중앙 저장소의 main으로 보내는지 확인하고 merge
	- pull을 먼저 하지 않아 중앙저장소와 원격저장소의 파일이 맞지 않으면 머지 불가
```
