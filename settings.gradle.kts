rootProject.name = "soa2"
include("service2")
include("service2:ejb-module")
findProject(":service2:ejb-module")?.name = "ejb-module"
