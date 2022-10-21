provider "aws" {
    region = "us-east-1"
}

variable "docker_image_version" {}

module "my_ecs_qa" {
	source = "../modules"
	env = "qa"
}