provider "aws" {
    region = "us-east-1"
}

variable "docker_image_version" {}

module "my_ecs_dev" {
	source = "../modules"
	env = "dev"
	docker_image_version = "${var.docker_image_version}"
}