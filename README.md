# CMXSI-PROJ2

Servidors virtuals complets (VPS)

## 1. Introducció
Es vol diferenciar l’oferta d’un ISP oferint als clients alguns extres. En aquest cas l’ISP oferirà un servei de
servidors virtualitats privats (VPS) de manera que pugui instal·lar totes les aplicacions que necessiti.

Un VPS és una solució més segura i estable que contractar un hosting compartit. En un VPS s’utilitza una
partició virtual d’un servidor físic al que se li assigna recursos exclusius i en el qual si pot instal·lar un
sistema operatiu que permet treballar amb més llibertat.
Alguns aspectes importants dels VPS son:
- Els problemes de trànsit d’altres usuaris no l’afecten
- S’obté accés de superusuari, per tant s’obté total llibertat dins del VPS
- Es té major privacitat, ja que les bases de dades estan bloquejades a altres usuaris del servidor
- Es pot escalar fàcilment, afegint més ram, cpu, espai de disc o ample de banda)

D’altra banda, al tenir control sobre el sistema VPS, requereix que el client tingui major coneixement tècnic
per administrar el servidor. Aquest punt pot portar a vulnerabilitats de seguretat si no es configura bé.

### 1.1 Plans d’allotjament
En general els proveïdors de VPS ofereixen diferents plans, els quals van escalen en recursos. Aquests
plans estan pensats per oferir recursos segons les necessitats/pressupost dels clients. Trobem els plans
més econòmics que tenen uns recursos més limitats per usuaris que no requereixin gaire potencia ni
emmagatzematge. A l’altre extrem plans que ofereixen els recursos necessaris per suportar una gran
quantitat de demanda (xarxa).

Pot ser que dins d’un mateix pla es pugui escalar una mica, es a dir que es puguin augmentar
lleugerament els recursos pagant el mateix. De forma que no és necessari canviar de pla si en algun
moment és necessiten recursos extres, sempre que no sigui un canvi molt significatiu.

D’altra banda també se sol poder escalar canviant de pla sense que suposi feina extra pel client
(reinstal·lar tot el subsistema en un altre servidor).

### 1.2 Objectiu
L’objectiu és instal·lar i configurar algun paquet que permeti crear i gestionar VPS per tal que el client
pugui tenir el control sobre el seu servidor. En apartats posteriors es veuran alguns programes que poden
servir per la gestió dels VPS.

A més s’haurà d’oferir una plataforma web per tal que el client pugui escollir el pla que millor satisfaci les
serves necessitats. Aquest apartat es farà o be oferint els plans mencionats anteriorment (oferir diferents
recursos de màquina segons el pla triat) o be oferint plantilles de servidors que ja tinguin software pre-
instal·lat com per exemple que ja porti un servidor apache instal·lat o algun altre software que pugui ser
d’interès pel client. La idea és com a mínim que es pugui triar entre diferents sistemes operatius i que un
cop finalitzada la instal·lació, se li doni al client les claus d’accés (usuari, password, ip) per connectar-se al
seu VPS.

També serà necessari un punt d’unió entre el servidor web i la creació o gestió dels VPS. Quan l’usuari trii
una de les opcions que s’ofereixen a la web, hi haurà d’haver algun procés que rebi la petició per tal de: o
bé crear un nou VPS o modificar les propietats d’un dels VPS existents (modificar recursos d’un VPS d’el
client).



### 2. Software de virtualització per els VPS

Es poden utilitzar diferents eines a diferents nivells que permetrien la gestió de VPS.

### 2.1 IaaS (Infrastructure as a Service):
En aquest nivell podem trobar per exemple OpenStack. És una plataforma de cloudComputing per treballar
en núvols públics o privats. Es composa de diferents mòduls que estan separats entre ells però que
interactuen. Entre altres, hi ha mòduls que serveixen per autenticació i d’altres que son només
d’emmagatzematge.

Com és pot intuir OpenStack serviria per gestionar els VPS dels clients però ofereix moltes més coses i
per funcionar requereix un gran esforç inicial per al final només oferir VPS. Per tant, per el tema que
ocupa, no s’utilitzarà aquest software.

### 2.2 Eines de Virtualització completa
En aquest cas ens trobem en l’extrem oposat. Aquestes eines permeten vitalitzar tot el hardware, com
memòria, processador, interfícies de xarxa o disc de forma que el VPS quedi aïllat dels altres. Aquestes
eines permeten instal·lar qualsevol sistema operatiu i a més donar total llibertat al client. Ofereixen
permisos totals sobre el VPS per tant el client podrà instal·lar qualsevol cosa dins, sempre que es
compleixi amb la limitació que ha contractat. En aquest apartat trobem eines com VirtualBox, VMWare o
KVM entre altres.
Qualsevol d’aquestes opcions pot ser vàlida per la gestió dels VPS.

### 2.3 Contenidors
Una opció una mica diferent és utilitzar eines de contenidors per gestionar els VPS. S’ha de tenir en
compte que els contenidors corren en el mateix kernel del sistema amfitrió i per tant estan lligats en certa
manera. Per exemple contenidors muntats sobre un sistema Linux no podrà tenir contenidors Windows ja
que el kernel no és el mateix.

Segons [3] existeixen diferents tipus de contenidors: contenidors d’aplicacions (com Docker) i contenidors
“Full System”. En principi (i segons la major part de blogs i posts) els contenidors d’aplicacions no poden
tenir un sistema operatiu propi sinó que estan pensats per córrer 1 sola aplicació, amb totes les
dependecies que necessiti. Si es vol poder instal·lar sistemes operatius complets dins d’un contenidor
s’han d’utilitzar els de tipus “Full System”.
A la pràctica, Docker per exemple te imatges de ubuntu o centos que, un cop arrancats, se’ls poden
instal·lar més paquets com apache o mysql, poden córrer més d’un servi alhora.

A priori, el que ofereixen els “Full system” és poder córrer contenidors amb un sistema operatiu complet, a
diferencia dels altres contenidors. Alguns software son OpenVz o LXC. D’altra banda tenen la mateixa
limitació que Docker, els contenidors corren tots sobre el kernel del sistema operatiu amfitrió.

Així doncs, els contenidors ofereixen un sistema de fitxers i dependències, aïllats per a cada contenidor,
però que no tenen un kernel propi i per tant depenen del sistema operatiu base.



## 3. Elecció de l’eina de virtualització

Com ja he comentat, descarto eines a nivell de IaaS ja que se’n van una mica del que es necessita per un
VPS. Per tant em quedo entre les eines de virtualització completa o els contenidors full System.

Pel que fa a eines de virtualització completa tenim: VMWare ESXI. Aquesta eina corre sobre un sistema
operatiu propi i manega les maquines virtuals a traves d’ell. D’altra banda tenim VirtualBox o KVM que
corren en sistemes operatius Windows o Linux.

KVM utilitza un kernel basat en Linux per tant només el podem instal·lar sobre un amfitrió Linux.
Pel que fa a VirtualBox potser està més pensat per la virtualització de maquines Desktop però també seria
una opció.

Per comoditat em quedaria o bé amb KVM o Virtualbox ja que els puc instal·lar en qualsevol ordinador que
tingui un Linux o un Windows.

En quant als contenidors tenim els contenidors orientats a aplicacions com Docker o bé contenidors
orientats a “Full System” com LXC o OpenVz. Pel que fa a OpenVz s’ha d’executar en un kernel concret,
per tant de moment el descarto.

Segons la informació que he anat trobant per internet es diu que amb Docker no es poden virtualitat
sistemes operatius, però com he comentat abans he pogut instal·lar una imatge de ubuntu i no he sabut
trobar una diferència significativa per tant diria que tant Docker com LXC poden servir.

Un punt a favor de Docker es que es pot instal·lar en diversos sistemes operatius amfitrions com Windows
a part de Linux. Per contra LXC només funciona sobre Linux i per tant només podríem tenir contenidors de
diferents distribucions Linux. Docker donaria diversitat en quant a sistemes operatius a oferir.

### 3.1 Proves realitzades

LXC i Docker

En el cas de LXC he pogut instal·lar tot el sistema i configurar-lo correctament. Després he pogut crear un
contenidor seleccionant la imatge d’un llistat predefinit de diferents distribucions de Linux. He instal·lat un
ubuntu 18.04.

El procediment és molt senzill i força ràpid. Un cop s’instal·la es pot fer una instrucció per loggar com a
root de la màquina amfitriona i crear els usuaris pels clients, així com instal·lar software addicional.

L’únic inconvenient de moment és que com a root de l’amfitrió es pot accedir a les màquines dels clients.
De moment no he trobat si es pot deshabilitar aquest accés.
Crec que es poder limitar alguns aspectes com la ram o espai de disc però no ho he provat. Si es pogués
fer seria senzill crear el sistema de plans diferents.
He seguit aquesta guia per instal·lar i provar LXC:

https://www.linuxjournal.com/content/everything-you-need-know-about-linux-containers-part-ii-working-
linux-containers-lxc

Per part de Docker, l’he instal·lat sobre un ubuntu 18.04 i dins he estirat una imatge de ubuntu amb un
Dockerfile que instal·la i executa openssh-server.

Un cop dins he pogut instal·lar i engegar un apache2 i un mysql. El problema que hi he trobat és que al
parar el contenidor i tornar-lo a encendre, els serveis apache2 i mysql estaven parats, per contra, com que
la imatge esta muntada perquè executi un openssh d’inici, aquest servei si que estava encès.

S’hauria de veure quin comportament tenen els contenidors entre LXC i Docker.


### 3.2 VM vs Contenidors
Els contenidors, a diferencia de la virtualització completa, no necessita virtualitzar elements com el disc, la
memòria o la CPU. A més com que tots els contenidors utilitzen el mateix kernel del sistema amfitrió per
tant també és mes lleuger que la virtualització completa.

En principi en contenidors també es millora la distribució dels recursos de la màquina base ja que hi ha
molts processos actius en un sistema operatiu complert que no son necessaris en contenidors.

Per tant el sentit comú ens diu que segurament podrem tenir moltes més instancies de contenidors com a
VPS que maquines virtuals completes.

De moment doncs intentaré treballar amb contenidors treballant com a VPS i concretament amb Docker ja
que, si funciona igual que LXC, tinc la possibilitat de poder instal·lar més sistemes operatius apart de
distribucions de Linux.


## 4 Instal·lació de Docker

### 4.1 Ubuntu 18.04

S’instal·larà Docker des del repositori oficial. Per fer-ho s’instalaran primer alguns paquets necessaris.
Primer de tot els paquets que permeten a ‘apt’ utilitzar HTTPS.

    sudo apt install apt-transport-https ca-certificates curl software-properties-common

Després s’afegeix la clau GPG per el repositori de Docker.

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add –

Afegim el repositori de Docker a les fonts ‘apt’

    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"

I finalment s’actualitza la base de dades de paquets

    sudo apt Update

Ara ja es pot instal·lar Docker a ubuntu.

    sudo apt install docker-ce

Només els usuaris amb privilegis poden utilitzar comandes de Docker. En concret només els usuaris
‘sudoers’ o bé els del grup ‘docker’. Podem afegir tots els usuaris necessaris al grup ‘docker’. Utilitzar
usuaris del grup ‘docker’ permet no haver de posar sudo davant totes les instruccions.
Es possible que després d’afegir un usuari al grup, sigui necessari iniciar sessió de nou per a que
s’apliquin els canvis.

    sudo usermod -aG docker <username>;


## 5. Creació d'un docker per VPS

Per crear un contenidor necessitem sempre crear-lo des d'una imatge base que conté tot el necessari perque el contenidor funcioni correctament.

Docker disposa d'una serie d'imatges oficials predefinides.  Per veure les imatges disponibles hi ha la comanda 'search'. Per exemple si volem veure totes les imatges relacionades amb apache fem:

    docker search apache
    
![imatges apache](images/captura1.png)

A part de serveis, també podem trobar imatges de diferents sistemes operatius. Com ja s'ha comentat s'ha d'anar en compte ja que els conenidors utilitzaran el mateix kernel que la màquina host, per tant podem trobar sistemes incompatibles.

Docker té versió tant de windows com de linux per tant podriem tenir contenidors amb els 2 sistemes, sempre que estiguin al host adequat.

### 5.1 Instal·lació d'un ubuntu mínim

Per fer el treball em centrarè en contenidors amb ubuntu, peró podria ser qualsevol sistema linux. 

La creació i posada en marxa d'un contenidor és molt simple. Ens fan falta les seguents comandes:

- __docker create 'imatge'__: Crea el contenidor amb la imatge seleccionada. Primer consulta les imatges locals, i si no te la 'imatge' intenta buscarla al repositori de Docker i la descarrega en local.

- __docker start 'nom contenidor'__: Un cop creada la imatge s'ha de posar en marxa amb aquesta comanda. Quan es crea el contenidor se li pot posar un nom amb el parametre __--name__. Si no l'hi hem posat nom docker n'hi posara un d'aleatori.

- __docker ps [-a]__: Ens mostra la informació bàsica de tots els contenidors encesos. Amb aquesta comanda podem veure el nom dels contenidors, si no els hi hem assignat. El paràmetre -a mostra tots els contenidors disponibles, engegats o parats.

- __docker run [-it] 'imatge'__: Aquesta comanda es la conjunció del __docker create__ i __docker start__. Si posem el paràmetre -it, entrarem al shell del contenidor.

Nota: En principi totes les comandes haurien de funcionar correctament pero en alguns casos, el docker create + start no m'ha funcionat amb ubuntu per tant utilitzo el docker run

Per veure totes les opcions disponibles de docker run veure [4]

Exemple creació de contenidor ubuntu:

![Creació contenidor](images/captura2.png)

Com es pot observar, ara la consola que es mostra és la del contenidor ubuntu i podem que només hi ha el proces del shell actiu.

Si fem exit el contenidor es pararà pero seguira existint. Si fem docker ps -a el podrem veure.

![Docker parat](images/captura3.png)


### 5.2 Contenidor ubuntu amb requeriments minims per servei VPS

En l'apartat anterior hem vist com crear un contenidor amb un sistema ubuntu. Si seguim aquells passos, només podrem accedir al docker de forma local. A part l'unic proces actiu al engegar el docker era el propi shell per tant, de cares a un VPS ens serveix de poc tal com està.

El primer pas seria instal·lar un servidor ssh perque el client del ISP pugui connectar-se a la seva "màquina virtual". Això es podria fer un cop accedim al shell del nou contenidor, però no seria gens automatitzable.

El que és més recomenable és fer que el contenidor ja tingui tot el que necessita un cop s'engega. D'aquesta manera, al rebre la petició del client, nomes caldrà crear i engegar el contenidor. El podrà connectar-se sense implicació del ISP.

El que se sol fer, es preparar imatges custom on, apart del sistema operatiu, indicarem tots els paquets necessaris que ha de tenir un contenidor.

Quan es crei un nou contenidor a partir d'aquestes imatges, ja disposarà de tot el necessari de forma automàtica. 

### 5.2.1 Creació d'una nova imatge amb Dockerfile

Per crear imatges custom necessitem crear un fitxer Dockerfile que contindrà totes les instruccions que s'hauran de realitzar al constuir la imatge. El fitxer Dockerfile funciona de forma similar a un makefile per compilar un programa.

Exemple de Dockerfile per a instal·lació de openssh:

    FROM ubuntu:18.04

    RUN apt-get update && apt-get install -y openssh-server
    RUN echo 'root:patates' | chpasswd
    RUN sed -ie '0,/#PermitRootLogin prohibit-password/s/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

    # SSH login fix. Otherwise user is kicked off after login
    RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

    ENV NOTVISIBLE "in users profile"
    RUN echo "export VISIBLE=now" >> /etc/profile

    EXPOSE 22
    CMD ["/usr/sbin/sshd", "-D"]


- __FROM__: A la linia From indiquem de quines imatges inicials volem partir. En aquests cas des d'una imatge de ubuntu. El format és [nom_imatge]:[versió/tag]
- __RUN__: Amb les comandes run podem executar comandes del sistema operatiu base per crear directoris o instal·lar paquets. En aquest cas s'instal·la un openss-server i es prepara perque l'usuari root pugui tenir acces.
- __ENV__: Es poden definir variables d'entorn que el contenidor tindrà disponibles un cop s'engegi.
- __EXPOSE__: Aquesta comanda enspermet tenir ports habilitats dins del contenidor. En aquest cas habilitem el port 22 ja que haurem de connectar per ssh
- __CMD__: Es la comanda que executarà el contenidor al iniciar-se.

Només podem tenir una linia CMD per tant, tot i que tenim un sistema operatiu i podem tenir més d'un servei actiu, d'inici només podem inciar una comanda.

 Docker està pensat per tenir serveis únics amb el mínim necessari per funcionar. Per això no te sentit fer que els contenidors executin d'inici més d'una aplicació. Això ens afectarà posteriorment quant necessitem aixecar més serveis dins del contenidor.


Un cop s'ha definit el Dockerfile amb totes les instruccions necessaries, s'ha de crear la nova imatge.

    docker build -t <nom_imatge> .

Com que podem tenir diferents Dockerfiles per crear diferents imatges, s'ha d'indicar el path del fitxer. En aquest cas és el directori actual.
Al fer el 'build' al paht seleccionat, Docker buscarà dins d'aquest directori el fitxer anomenat Dockerfile i l'executarà.

Un cop s'ha creat la nova imatge podem veure-la amb la comanda:

    docker images

Aquesta comanda llista totes les imatges locals que tenim. Com que hem utilitzat la imatge base 'ubuntu:18.04' aquesta també la tindrem, ja que l'ha descarregat per montar la nova imatge.


### 5.2.2 Iniciar contenidor amb la nova imatge ssh

S'utilitzarà la nova imatge per a crear un nou contenidor que tingui el servei de ssh engegat. Fem servir la comanda docker run per crar el nou contenidor i posar-lo en marxa.

    docker run -d -p 30000:22 <nom_imatge>

Com es pot veure és necessari fer la relació de ports de la màquina real al contenidor. Si no ho fessim no podriem accedir al contenidor per ssh. Tot i que utilitzi el port 22 com hem vist abans, el contenidor no té @IP per tant ens hem de dirigir a ell des de la màquina real. 

De moment no he trobat cap manera de reassignar ports o obrir-ne de nous un cop el contenidor està creat. Es possible que és pugui, però per el moment l'única manera és parant el contenidor i crear-ne un de nou a partir de l'actual, assignant tots els ports necessaris amb el parametre -p.

Amb el paràmetre -d indiquem que el conenidor s'iniciará en mode 'detached' és a dir que no estarà connectat a la consola, o bé que correrà en background. Això és important ja que si no ho fem, el cicle de vida del contenidor estarà lligat al terminal.

Un cop s'ha encés el contenidor podem accedir a ell via ssh.

![acces ssh nou contenidor](images/captura4.png)

Si mirem quins processos hi ha actius veurem que només hi a la consola i el servei de ssh.

Un cop dins del servidor, el client podria instal·lar qualsevol paquet que volgues. L'inconvenient és que si necessita accés al exterior s'haurà d'indicar abans de crear el contenidor.

Un problema més gran és en relació al proces que ha iniciat el contenidor. Si no especifiquem res més no tindrem acces al systemctl i per tant no podrem aixecar nous servies.

![Operació no permesa](images/captura5.png)








## Bibliografia
[1] Que és un VPS:

https://www.hostinger.es/tutoriales/que-es-un-vps

[2] Virtualització amb contenidors

https://www.ionos.es/digitalguide/servidores/know-how/alternativas-a-los-contenedores-en-docker/

[3] LXC vs KVM

https://www.skysilk.com/blog/2019/lxc-vs-kvm/

[4] Documentació docker run

https://docs.docker.com/engine/reference/run/