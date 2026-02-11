db = db.getSiblingDB("statusdb");

db.createUser({
  user: "status_user",
  pwd: "123456",
  roles: [
    {
      role: "readWrite",
      db: "statusdb"
    }
  ]
});

db.createCollection("orderStatus");
