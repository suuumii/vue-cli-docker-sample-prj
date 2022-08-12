# docker + vue-cliのサンプルプロジェクト

# Command
- イメージの作成とコンテナの起動
    - `docker-compose up --build`
    - --build = ( docker-compose build && docker-compose up app )

- コンテナ内でbashを起動
    - `docker exec -it vuecli3 bash`

# VSCodeでコンテナの中で編集する方法
- それぞれのソースディレクトリをルートでVSCodeを開く
  - 例）client_app_2フォルダを右クリック「codeで開く」
- 右下に出てくる「Reopen in Container」をクリック

# 参考
- [Docker上にVue.jsの開発環境を構築する方法。docker-composeで簡単に作成する手順](https://prograshi.com/platform/docker/create-vuejs-on-docker/)
- [VSCode Remote Containerで複数コンテナに接続する │ wonwon eater](https://wonwon-eater.com/vscode-remote-container-multi/)
- [Docker Compose な開発環境にちょい足し3分で作るVSCode devcontainer](https://zenn.dev/saboyutaka/articles/9cffc8d14c6684)
- [ログインした後の認証処理｜SpringとVue.jsで作るログイン機能付きガチャ](https://zenn.dev/misaka/books/9734700544990d/viewer/7f798a)