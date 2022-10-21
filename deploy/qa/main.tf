provider "aws" {
    region = "us-east-1"
}

module "my_ecs_qa" {
	source = "../modules"
	env = "qa"
}