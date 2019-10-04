# yandex-cloud-sdk-test

Set env variables:
* YC_OAUTH - user oauth token
* YC_FOLDER_ID - folder id

Run - `mvn exec:java`

Run with reading envs from yc config:
```
YC_OAUTH=$(yc config get token) YC_FOLDER_ID=$(yc config get folder-id) mvn exec:java
```