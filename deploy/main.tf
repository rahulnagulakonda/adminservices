provider "aws" {
    region = "us-east-1"
}

resource "aws_ecs_task_definition" "mytaskdefinition" {
    requires_compatibilities = ["FARGATE"]
    family = "adminservicestask"
    network_mode = "awsvpc"
    execution_role_arn = "arn:aws:iam::566544666953:role/ecsTaskExecutionRole"
    memory = 2048
    cpu = 1024
    container_definitions = jsonencode(
        [
            {
            "name" : "adminservicescontainer",
            "image" : "566544666953.dkr.ecr.us-east-1.amazonaws.com/interon:latest",
            "memory" : 1024,
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
    name = "adminservicescluster"
}

resource "aws_ecs_service" "myclutserservice" {
    launch_type = "FARGATE"
    task_definition = aws_ecs_task_definition.mytaskdefinition.arn
    cluster = aws_ecs_cluster.mycluster.id
    name = "adminservicesservice"
    desired_count = 1
    deployment_maximum_percent         = "200"
    deployment_minimum_healthy_percent = "75"
    network_configuration {
        subnets = ["subnet-0f66e0aeb8ddeeaf5"]
        security_groups = ["sg-0be7ee4e7026821e3"]
        assign_public_ip = "true"
    }
}
