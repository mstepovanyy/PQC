Producer->Queue->Consumer
- Every X seconds Producer enqueues Y messages in parallel 
- Consumer is polling queue by portions: Z messages every N seconds
- Consumer has pool of threads to process messages, if no threads available, consumer is not polling
- Processing time can be from 1ms up to few minutes

Requirements: 
- java 8 and libraries (like javaslang, vavr if needed)
- service should be resilient, so in case of any processing issues we can loose some messages but service should survive