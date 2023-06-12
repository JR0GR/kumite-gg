var db = connect("mongodb://root:pestillo@localhost:27017/admin");

db = db.getSiblingDB('kumite-gg'); // we can not use "use" statement here to switch db

db.createUser(
    {
        user: "jr",
        pwd: "pestillo",
        roles: [ { role: "readWrite", db: "kumite-gg"} ],
    }
)