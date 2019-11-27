FILES=	README.md \
	README.TXT \
	package.bluej \
	Makefile \
	Main.java \
	Jogador.java \
	TipoNavio.java \
	TipoJogador.java \
	TipoCasa.java \
	TipoOrientacao.java \
	Tabuleiro.java \
	BatalhaNaval.java

all:		run

compile:
		@javac *.java

run:		compile
		@java Main

javadoc:
		@javadoc *.java

init:
		@git init
		@ssh-keygen -t rsa -C "Roland Teodorowitsch"

add:
		@for i in ${FILES}; do git add $$i ; done

commit:		add
		@git commit -m "`date`"
#		@git remote add origin https://github.com/RolandTeodorowitsch/BatalhaNaval.git
		@git push -u origin master

clean:
		@rm -f *.class *.ctxt
