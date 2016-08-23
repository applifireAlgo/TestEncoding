




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/YKIGFTLDFZTHM3LFFRMW
ART_CREATE_PATH=/tmp/applifire/db/YKIGFTLDFZTHM3LFFRMW/art/create
AST_CREATE_PATH=/tmp/applifire/db/YKIGFTLDFZTHM3LFFRMW/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=project3
USER=project3
PASSWORD=project3
PORT=3306
HOST=localhost


echo 'resetCounter Starts...'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "ALTER TABLE ast_AddressMap_B AUTO_INCREMENT = 1; ALTER TABLE ast_CommunicationMap_B AUTO_INCREMENT = 1; ";

echo 'resetCounter ends...'

