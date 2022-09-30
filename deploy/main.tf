provider "aws" {
	region = "us-east-1"
}

resource "aws_iam_user" "mytfiamuser" {
	name = "my_tf_iam_user"
}
