# 【Level3-3】

## テーマ
既存プログラムを参考に、CRUD機能が作成できること
ページ遷移とバリデーションの仕組みが理解できること
templateへの記載ができるようになること

## 演習課題の準備（開発環境について）
* この演習では、Codespacesと呼ばれるサービスを使って開発を行います。
* ブラウザ上で動作する開発環境です、インストール不要で使う事ができます。
* レベル0で実施した[手順](/Codespacesの実行手順.md)を参照して、演習課題の準備をしましょう。

## 演習内容
Lesson(授業管理機能)を参考にArchievement(実績管理機能)の実績登録画面を作成しましょう。

## 演習内容詳細
実績登録画面を作成しましょう。<br>
実績管理画面のNewボタンを押下して遷移します。<br>
![image](https://user-images.githubusercontent.com/32722128/191149466-f1ee03aa-7c39-4416-9877-db0b12f41fa7.png)
![image](https://user-images.githubusercontent.com/32722128/191149705-d01f3bbc-fa4c-4893-bfc3-0bb8ebd0f662.png)
以下の順序で実装を行ってください。

1. リクエスト情報を保持する、Formクラスである`ArchievementForm`クラスを作成してください。
2. `ArchievementController`クラスに、登録画面を表示するために、`regist`メソッドを作成してください。
3. 登録画面で入力されたデータを格納するために、`ArchievementDao`クラスに`insertArchievement`メソッドを追加してください。
4. `ArchievementService`クラスに`insert`メソッドを追加し、`ArchievementDao`クラスの`insertArchievement`メソッドを呼び出してください。
5. `ArchievementController`クラスに、登録のためのリクエストを受け付ける`insert`メソッドを作成してください。

それぞれに対応する、授業管理機能(Lesson)のファイルを参考に作成してください。

|Archievement|Lesson|
|---|---|
|ArchievementController|LessonController|
|ArchievementService|LessonService|
|ArchievementForm|LessonForm|
|Archievement|Lesson|
|ArchievementDao|LessonDao|

## 1.Formクラスの作成
まずはFormクラスを作成しましょう、`ArchievementForm.java`ファイルを作成してください。<br>
ブラウザから送信されたリクエストデータを保持するためのクラスです。<br>
画面上の入力項目にフィールド変数が対応するように作成します。<br>
ビューのテンプレートファイルとして`~\templates\archievement\new.html`がすでに用意されています。<br>
このテンプレートファイルでは、`name`と`memo`という名前のフィールド変数が存在する、クラスのオブジェクトをやり取りして、リクエスト送信する、画面表示を行うようになってますので、`name`と`memo`という名前のフィールド変数持つクラスを作成します。<br>
それぞれのフィールド変数は最終的には、Entityクラスのオブジェクトに格納され、データベースにinsertされます、Entityクラスの対応するフィールド変数とデータ型は同じにしておきましょう。<br>
フィールド変数`name`には`@NotEmpty(message = "入力してください")`とアノテーションを付与してください、こうする事で、`name`項目をフォームから入力する際に必須項目とすることが出来ます。  <br>
前回の演習内容を参考にConstructor(コンストラクター)と、Getter/Setter(ゲッター/セッター)を作成してください。

## 2.`ArchievementController`クラスに`regist`メソッドを追加
`regist`メソッドを作成してください、`/archievement/new`のパスに対してリクエストが送信された際に、このメソッドが使われれるように`@GetMapping("/new")`とアノテーションを付与してください。  <br>
入力項目に何も入力されていない状態で画面表示を行いたいので、`model`オブジェクトの属性値`archievementForm`に空のオブジェクト(`new ArchievementForm()`)を格納し、戻り値にビューのテンプレートファイル、`archievement/new`を設定してください。

## 3.`ArchievementDao`クラスに`insertArchievement`メソッドを追加
`ArchievementDao`クラスに`insertArchievement`メソッドを追加してください。<br>
戻り値のデータ型は`int`、引数は`Archievement`クラスのオブジェクトを受け付けるようにしてください。<br>
`selectAll`メソッドを作ったように、インターフェイス、実装クラスどちらにも追加してください。<br>
変数`sql`に、`ARCHIEVEMENT`テーブルにデータを追加するためのINSERT文を設定してください。<br>
この時VALUESのかっこ内の値を指定する個所には、「?」を指定してください。<br>
```
String sql = "INSERT INTO ARCHIEVEMENT (name, memo) VALUES(?, ?)";
```
「?」はバインドパラメータと呼ばれる物です、データベースに対して、一度SQL文を送信した後、バインドパラメータに入れ込む値を別で送信します。<br>
バインドパラメータを使用する事で、SQLインジェクションを防ぐ事が出来ます。<br>
`JdbcTemplate`クラスの`update`メソッドを使用してSQL文を実行します。<br>
第一引数には、SQL文を、第二引数以降は、バインドパラメータに指定したい値を設定します。<br>
戻り値として、実行されたSQL文が影響を与えた行数が返却されます。<br>
そのまま、`insertArchievement`メソッドの戻り値として返して下さい。<br>
今回は1件INSERTを行っただけですので1が戻り値として返されるはずです。<br>

## 4.`ArchievementService`クラスに`insert`メソッドを追加
`ArchievementService`クラスに`insertArchievement`メソッドを追加してください。<br>
戻り値はなし(voidを指定)、引数は`Archievement`クラスのオブジェクトを受け付けるようにしてください。<br>
`selectAll`メソッドを作ったように、インターフェイス、実装クラスどちらにも追加してください。<br>
先ほど実装した、`ArchievementDao`クラスの`insertArchievement`メソッドを呼び出し、実行されたSQL文が影響を与えた行数を取得してください。<br>
0が返された場合は、INSERTの処理が正常に完了してませんので、`NotInsertException`クラスの例外を投げるようにしてください。<br>

## 5.`ArchievementController`クラスに`insert`メソッドを追加
`ArchievementController`クラスに、登録のためのリクエストを受け付ける`insert`メソッドを追加してください。<br>
ビューのテンプレートファイル、`archievement/new`にて、登録ボタンを押した際には、パス`/archievement/insert`に対して、POSTリクエストを送信するように指定されています。<br>
このリクエストを受け取る事が出来るように、`@PostMapping("/insert")`とアノテーションを付与してください。<br>
引数部分は以下のように設定してください、それぞれの引数と付与されているアノテーションの意味について解説します。<br>
```
@PostMapping("/insert")
  public String insert(
      @Validated @ModelAttribute("archievementForm") ArchievementForm archievementForm,
      BindingResult result, Model model) {
```
* @Validated @ModelAttribute("archievementForm") ArchievementForm archievementForm
  * @Validated ・・・ バリデーション(入力チェックを有効にするアノテーションです。)
  * @ModelAttribute("archievementForm") ・・・ 属性名の指定が`archievementForm`であるリクエストデータを引数に格納する事を示しています。
* BindingResult result ・・・ 入力チェックの結果などが格納されます。
* Model model ・・・ ビューに値を渡すための引数です。

入力チェック結果にエラーが存在しないか確認し、存在すれば、再度画面表示を行うように指定します。<br>
この時入力チェックの結果も返されます、入力チェックの結果をテンプレートエンジンが確認し、赤字でエラーメッセージを表示するようHTMLの生成を行っています。
```
if (result.hasErrors()) {
  model.addAttribute("archievementForm", archievementForm);
  return "archievement/new";
}
```
その後、Formクラスのオブジェクトから、Entityクラスのオブジェクトに入力データを移し替え、`ArchievementService`クラスの`insertArchievement`メソッドを呼び出し、登録処理を実施します。
```
Archievement archievement = new Archievement();
archievement.setName(archievementForm.getName());
archievement.setMemo(archievementForm.getMemo());

archievementService.insertArchievement(archievement);
```
その後、`/archievemen`のパスに対してリダイレクトを行うよう、戻り値に指定します。<br>
リダイレクトする事によって、同クラスの`index`メソッドが実行され、実績一覧の画面が表示されます。

## 課題の提出
* 課題の提出は[課題提出の操作](/課題の提出手順.md)のコミット・プッシュ・プルリクエストを実施しましょう。

