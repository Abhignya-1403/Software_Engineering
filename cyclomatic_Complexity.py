def cyclomatic_complexity():
    nodes = int(input("Enter the number of nodes(N): "))
    edges = int(input("Enter the number of edges(E): "))
    connected_components = int(input("Enter the number of connected components(P,usually 1): "))
    complexity = edges-nodes+2*connected_components
    return complexity
complexity = cyclomatic_complexity()
print(f"Cyclomatic complexity: {complexity} ")


#Enter the number of nodes(N): 5
#Enter the number of edges(E): 5
#Enter the number of connected components(P,usually 1): 1
#Cyclomatic complexity: 2 