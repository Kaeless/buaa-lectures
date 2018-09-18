package main

import (
	"golang.org/x/net/context"
	"google.golang.org/grpc"
	"log"
	"net"
	"time"
	pb "../../org_feuyeux_given_proto"
	"strconv"
	"google.golang.org/grpc/reflection"
	google_protobuf "github.com/golang/protobuf/ptypes/empty"
)

const (
	port = ":50061"
)

type protoServer struct{}

func (s *protoServer) TalkChunk(e *google_protobuf.Empty, c pb.LandingService_TalkChunkServer) error {
	return nil
}

func (s *protoServer) Talk(ctx context.Context, in *pb.TalkRequest) (*pb.TalkResponse, error) {
	result := new(pb.TalkResult)
	result.Id = time.Now().UnixNano()
	result.Type = pb.ResultType_SEARCH
	kv := make(map[string]string)
	kv["data"] = in.Data
	kv["meta"] = in.Meta
	kv["timestamp"] = strconv.Itoa(time.Now().Nanosecond())
	result.Kv = kv

	return &pb.TalkResponse{
		Status:  200,
		Results: []*pb.TalkResult{result},
	}, nil
}

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	pb.RegisterLandingServiceServer(s, &protoServer{})
	// Register reflection service on gRPC server.
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
