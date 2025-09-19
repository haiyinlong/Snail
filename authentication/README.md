## 认证服务

处理用户的登录认证相关操作。

* 登录用户的注册
* 登录用户的认证
* 这里可以记录用户的基本信息如：用户类型（内部、外部、合作方等）、部门、邮件

基于Spring-authentication-service 实现统一认证

`AuthorizationServerConfig` 是Oauth2相关配置
`SecurityConfig` 是Spring Security的配置

生成密钥对,可用于签名token,针对不同的环境使用不同过的密钥对

```bash
keytool -genkeypair \
  -alias snail-auth-server \
  -keyalg RSA \
  -keysize 2048 \
  -storetype JKS \
  -keystore snail-auth-server-dev.jks \
  -validity 3650 \
  -storepass snail-ken \
  -keypass snail-ken \
  -dname "CN=OAuth2 Authorization Server, OU=Dev, O=Haiyinlong, L=ShenZhen, ST=ShenZhen, C=CN"
```

> 注意要替换掉 snail-auth-server-dev.jks 文件，不可以放在开源项目中。

### 退出登录

通过调用`connect/logout` 接口退出登录,携带`id_token_hint`参数 和
`post_logout_redirect_uri`参数

* id_token_hint: 登录用户的id_token, 注意不是token
* post_logout_redirect_uri: 登出成功后的跳转地址，在创建RegisterClient的时候配置的指定地址。
  要进行`encodeURIComponent` 编码

> http://192.168.5.228:9001/auth/connect/logout?post_logout_redirect_uri=http%3A%2F%2F127%2E0%2E0%2E1%3A8080%2F&id_token_hint=eyJ4NXQjUzI1NiI6IjNEcTBhclQyNGhYeG5QVks3QnlaRzBVbmJ1N1FnOVZwX0U2VmhCMjdMbVkiLCJraWQiOiJzbmFpbC1hdXRoLXNlcnZlciIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIxIiwicm9sZXMiOiJBRE1JTiIsImlzcyI6Imh0dHA6Ly8xOTIuMTY4LjUuMjI4OjkwMDEvYXV0aCIsImRlcHRJZCI6IjEiLCJkZXB0Ijoi57O757uf6YOo6ZeoIiwidXNlcklkIjoiMSIsInNpZCI6IjVDMXN4dkREZTNlQ2xBdHRNZEUteXBrcU1lS3RzdzlqWi1UbFRsZDlXOWciLCJhdWQiOiI2ZTAzNTNkNi02MzM1LTQzMDItOGVkYi0zZjU2MWY5OWMwNDUiLCJyZWFsTmFtZSI6IueuoeeQhuWRmCIsImF6cCI6IjZlMDM1M2Q2LTYzMzUtNDMwMi04ZWRiLTNmNTYxZjk5YzA0NSIsImF1dGhfdGltZSI6MTc1ODI2NzY5OSwiZXhwIjoxNzU4MjY5NTE0LCJpYXQiOjE3NTgyNjc3MTQsImp0aSI6IjkzZjA5MjkxLTgwYWMtNDIxYS04MWE5LWE1OThmMDk0NjdlOCIsImVtYWlsIjoiYWRtaW5AcXEuY29tIn0.CbyWDdJIzx3rqOfBtNNDXdvpUOihm4cjek0deQ1SXIrp9RSjSoy0QrXWFdwtlQCNT3Cr9Ru4LJBgyyf8nxfkzIXL-QtXAow9rxOOreV3uFir3k6vJgV2UxhRVb3u_AN7LdOrttIKeaj54ynKTr1NnGn10Xvo6q5Gt4PTHw-gkkdGtQJNfNAMJy0WGwo5qoxK0CkJpm6WyH7Q3JAWQGX3W3IngVmtkzT8aBFVH8QX0kGWO7V0utS6p49RHRST_6VGggK2qXL91glcRUskbeN1db3V6u8ktIr3YvHo4SrLlbmM1jsa9HvLcLQMBhImIKkiJF_Tw8lCC_SWPh_w862goA
