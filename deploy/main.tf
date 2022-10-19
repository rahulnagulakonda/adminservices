variable "env" {}

//variable "docker_image_version" {}

provider "aws" {
    region = "us-east-1"
}

//ecs task for development
resource "aws_ecs_task_definition" "mytaskdefinition" {
    requires_compatibilities = ["FARGATE"]
    family = "adminservices_task_${var.env}"
    network_mode = "awsvpc"
    execution_role_arn = "arn:aws:iam::429055212256:role/ecsTaskExecutionRole"
    memory = 2048
    cpu = 1024
    container_definitions = jsonencode(
        [
            {
            "name" : "adminservices_container_${var.env}",
            "image" : "429055212256.dkr.ecr.us-east-1.amazonaws.com/interon:latest",
            "memory" : 1024,
            "portMappings" : [
                {
                    "containerPort" : 8082,
                }
            ],
            "environmentFiles": [
                {
                    "value": "arn:aws:s3:::adminservicesbucket/${var.env}.env",
                    "type": "s3"
                }
            ],
            "logConfiguration": {
            	"logDriver": "awslogs",
          		"options": {
            		"awslogs-group": "/ecs/adminservices/${var.env}",
            		"awslogs-region": "us-east-1",
            		"awslogs-stream-prefix": "ecs"
          		}
        	}
            }
            
        ]
    )

}

resource "aws_ecs_cluster" "mycluster" {
    name = "adminservices_cluster_${var.env}"
}

resource "aws_ecs_service" "myclutserservice" {
    launch_type = "FARGATE"
    task_definition = aws_ecs_task_definition.mytaskdefinition.arn
    cluster = aws_ecs_cluster.mycluster.id
    name = "adminservices_service_${var.env}"
    desired_count = 1
    deployment_maximum_percent         = "200"
    deployment_minimum_healthy_percent = "100"
    network_configuration {
        subnets = ["subnet-0a23c6f570616fa44"]
        security_groups = ["sg-07a278ab490208e09"]
        assign_public_ip = "true"
    }
}

