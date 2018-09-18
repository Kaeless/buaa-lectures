package main

import (
	"golang.org/x/net/context"
	"google.golang.org/grpc"
	"log"
	pb "../../org_feuyeux_given_proto"
)

const (
	address = "localhost:50061"
)

func main() {
	conn, err := grpc.Dial(address, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	c := pb.NewLandingServiceClient(conn)

	r, err := c.Talk(context.Background(), &pb.TalkRequest{Data: "query=ai,from=0,size=1000,order=x,sort=y", Meta:"user=eric"})
	if err != nil {
		log.Fatalf("fail to talk: %v", err)
	}
	log.Printf("talk response: %d %s", r.Status, r.Results)
}