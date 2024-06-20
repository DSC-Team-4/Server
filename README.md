# Server

## Spring Server 실행 방법
1. main 브랜치로 이동
2. git pull origin main 명령어 실행
3. Server/server 디렉토리 이동 (cd /Server/server 명령어 입력)
4. gradlew 빌드 실행 (./gradlew build 명령어 입력)
5. Server/server/build/libs (cd ./build/libs 명령어 입력)
6. jar 파일 실행 (nohup java -jar -Duser.timezone=Asia/Seoul server-0.0.1-SNAPSHOT.jar & 명령어 입력)
