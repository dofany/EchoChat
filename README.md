에코서버 구현
#소켓을 이용하여 서버에서 자신이 채팅을 치면 클라이언트에는 에코로 돌아오는 채팅

ex) 서버: 안녕? / 클라: 안녕?

<서버>

서버 : Socket => Bind => Accept => Read => Write => Close

서버.Write => 클라.Read

<클라이언트>

클라 : Socket => Connect => Write => Read => Close

클라.Connect => 서버.Accept

클라.Write => 서버.Read
