provider "aws" {
    region = "us-east-1"
}

module "my_ecs_dev" {
	source = "../modules"
	env = "dev"
}