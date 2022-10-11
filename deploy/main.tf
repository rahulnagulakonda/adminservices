provider "aws" {
    region = "us-east-1"
}

resource "aws_ecs_task_definition" "mytaskdefinition" {
    requires_compatibilities = ["FARGATE"]
    family = "adminservices_task"
    network_mode = "awsvpc"
    execution_role_arn = "arn:aws:iam::429055212256:role/ecsTaskExecutionRole"
    memory = 512
    cpu = 256
    container_definitions = jsonencode(
        [
            {
            "name" : "adminservices_container",
            "image" : "429055212256.dkr.ecr.us-east-1.amazonaws.com/interon:latest",
            "memory" : 256,
            "portMappings" : [
                {
                    "containerPort" : 8082,
                }
            ]
            }
            
        ]
    )

}

resource "aws_ecs_cluster" "mycluster" {
    name = "adminservices_cluster"
}

resource "aws_ecs_service" "myclutserservice" {
    launch_type = "FARGATE"
    task_definition = aws_ecs_task_definition.mytaskdefinition.arn
    cluster = aws_ecs_cluster.mycluster.id
    name = "adminservices_service"
    desired_count = 1
    deployment_maximum_percent         = "200"
    deployment_minimum_healthy_percent = "75"
    network_configuration {
        subnets = ["subnet-0a23c6f570616fa44"]
        security_groups = ["sg-07a278ab490208e09"]
        assign_public_ip = "true"
    }
}
