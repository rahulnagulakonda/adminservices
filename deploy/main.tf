provider "aws" {
    region = "us-east-1"
}

//ecs task for development
resource "aws_ecs_task_definition" "mytaskdefinitiondev" {
    requires_compatibilities = ["FARGATE"]
    family = "adminservices_task_dev"
    network_mode = "awsvpc"
    execution_role_arn = "arn:aws:iam::429055212256:role/ecsTaskExecutionRole"
    memory = 2048
    cpu = 1024
    container_definitions = jsonencode(
        [
            {
            "name" : "adminservices_container",
            "image" : "429055212256.dkr.ecr.us-east-1.amazonaws.com/interon:latest",
            "memory" : 1024,
            "portMappings" : [
                {
                    "containerPort" : 8082,
                }
            ],
            "environmentFiles": [
                {
                    "value": "arn:aws:s3:::adminservicesbucket/dev.env",
                    "type": "s3"
                }
            ],
            "logConfiguration": {
            	"logDriver": "awslogs",
          		"options": {
            		"awslogs-group": "/ecs/adminservices/dev",
            		"awslogs-region": "us-east-1",
            		"awslogs-stream-prefix": "ecs"
          		}
        	}
            }
            
        ]
    )

}

resource "aws_ecs_cluster" "myclusterdev" {
    name = "adminservices_cluster_dev"
}

resource "aws_ecs_service" "myclutserservicedev" {
    launch_type = "FARGATE"
    task_definition = aws_ecs_task_definition.mytaskdefinitiondev.arn
    cluster = aws_ecs_cluster.myclusterdev.id
    name = "adminservices_service_dev"
    desired_count = 1
    deployment_maximum_percent         = "200"
    deployment_minimum_healthy_percent = "100"
    network_configuration {
        subnets = ["subnet-0a23c6f570616fa44"]
        security_groups = ["sg-07a278ab490208e09"]
        assign_public_ip = "true"
    }
}

//ecs task for qa
resource "aws_ecs_task_definition" "mytaskdefinitionqa" {
    requires_compatibilities = ["FARGATE"]
    family = "adminservices_task_qa"
    network_mode = "awsvpc"
    execution_role_arn = "arn:aws:iam::429055212256:role/ecsTaskExecutionRole"
    memory = 2048
    cpu = 1024
    container_definitions = jsonencode(
        [
            {
            "name" : "adminservices_container",
            "image" : "429055212256.dkr.ecr.us-east-1.amazonaws.com/interon:latest",
            "memory" : 1024,
            "portMappings" : [
                {
                    "containerPort" : 8082,
                }
            ],
            "environmentFiles": [
                {
                    "value": "arn:aws:s3:::adminservicesbucket/qa.env",
                    "type": "s3"
                }
            ],
            "logConfiguration": {
            	"logDriver": "awslogs",
          		"options": {
            		"awslogs-group": "/ecs/adminservices/qa",
            		"awslogs-region": "us-east-1",
            		"awslogs-stream-prefix": "ecs"
          		}
        	}
            }
            
        ]
    )

}

resource "aws_ecs_cluster" "myclusterqa" {
    name = "adminservices_cluster_qa"
}

resource "aws_ecs_service" "myclutserserviceqa" {
    launch_type = "FARGATE"
    task_definition = aws_ecs_task_definition.mytaskdefinitionqa.arn
    cluster = aws_ecs_cluster.myclusterqa.id
    name = "adminservices_service_qa"
    desired_count = 1
    deployment_maximum_percent         = "200"
    deployment_minimum_healthy_percent = "100"
    network_configuration {
        subnets = ["subnet-0a23c6f570616fa44"]
        security_groups = ["sg-07a278ab490208e09"]
        assign_public_ip = "true"
    }
}