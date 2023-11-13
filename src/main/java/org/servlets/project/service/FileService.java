package org.servlets.project.service;
import org.servlets.project.model.File;
import org.servlets.project.repository.FileRepository;
import org.servlets.project.repository.aipRepository.FileRepositoryImpl;
import java.util.List;
public class FileService {
    private final FileRepository fileRepository;
    public FileService() {
        this.fileRepository = new FileRepositoryImpl();
    }
    public File createNewFile(File file){
        return fileRepository.save(file);
    }
    public List<File>getAllFile(){
        return fileRepository.getAll();
    }
    public File updateFileById(File file, Long id){
        return fileRepository.update(file,id);
    }
    public boolean deleteById(Long id){
        fileRepository.deleteById(id);
        return true;
    }
    public File getByIdFile(Long id){
        return fileRepository.getId(id);
    }
}
